/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.Messages.CAPABILITIES_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Messages.HOSTNAME_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Messages.INVALID_BROWSER;
import static com.github.wasiqb.boyka.enums.Messages.INVALID_REMOTE_URL;
import static com.github.wasiqb.boyka.enums.Messages.PASSWORD_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.enums.Messages.PROTOCOL_REQUIRED_FOR_HOST;
import static com.github.wasiqb.boyka.enums.Messages.USER_NAME_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.sessions.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static io.github.bonigarcia.wdm.WebDriverManager.operadriver;
import static io.github.bonigarcia.wdm.WebDriverManager.safaridriver;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.net.MalformedURLException;
import java.net.URL;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.config.ui.WebSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.exception.FrameworkError;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class DriverManager {
    private static final Logger LOGGER = getLogger ();

    /**
     * Closes the driver instance and clears driver session.
     */
    public static void closeDriver () {
        LOGGER.traceEntry ();
        LOGGER.info ("Closing driver instance");
        getSession ().getDriver ()
            .quit ();
        clearSession ();
        LOGGER.traceExit ();
    }

    /**
     * Creates driver instance.
     *
     * @param applicationType the application type
     * @param driverKey the driver config key
     *
     * @return the driver instance
     */
    public static DriverManager createDriver (final ApplicationType applicationType, final String driverKey) {
        LOGGER.traceEntry ();
        LOGGER.info ("Creating Driver Instance for {} and driver key: {}", applicationType, driverKey);
        final var instance = new DriverManager (applicationType, driverKey);
        instance.setupDriver ();
        return LOGGER.traceExit (instance);
    }

    private final ApplicationType  applicationType;
    private final String           driverKey;
    private final FrameworkSetting setting;

    private DriverManager (final ApplicationType applicationType, final String driverKey) {
        LOGGER.traceEntry ();
        this.applicationType = applicationType;
        this.driverKey = driverKey;
        this.setting = loadSetting ();
        LOGGER.traceExit ();
    }

    private Capabilities getCapabilities (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var capabilities = requireNonNull (webSetting.getCapabilities (),
            CAPABILITIES_REQUIRED_FOR_REMOTE.getMessage ());
        final var remoteCapabilities = new DesiredCapabilities ();
        capabilities.forEach (remoteCapabilities::setCapability);
        return LOGGER.traceExit (remoteCapabilities);
    }

    private String getHostName (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        if (requireNonNullElse (webSetting.getCloud (), CloudProviders.NONE) != CloudProviders.NONE) {
            final var hostNamePattern = "{0}:{1}@{2}";
            return format (hostNamePattern,
                requireNonNull (webSetting.getUserName (), USER_NAME_REQUIRED_FOR_CLOUD.getMessage ()),
                requireNonNull (webSetting.getPassword (), PASSWORD_REQUIRED_FOR_CLOUD.getMessage ()),
                requireNonNull (webSetting.getHost (), HOSTNAME_REQUIRED_FOR_REMOTE.getMessage ()));
        }
        return LOGGER.traceExit (requireNonNull (webSetting.getHost (), HOSTNAME_REQUIRED_FOR_REMOTE.getMessage ()));
    }

    private URL getRemoteUrl (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var URL_PATTERN = "{0}://{1}/wd/hub";
        final var hostName = new StringBuilder (getHostName (webSetting));
        if (webSetting.getPort () != 0) {
            hostName.append (":")
                .append (webSetting.getPort ());
        }
        final var url = format (URL_PATTERN, requireNonNull (webSetting.getProtocol (),
            format (PROTOCOL_REQUIRED_FOR_HOST.getMessage (), hostName)).name ()
            .toLowerCase (), hostName);
        try {
            return LOGGER.traceExit (new URL (url));
        } catch (final MalformedURLException e) {
            LOGGER.catching (e);
            throw new FrameworkError (INVALID_REMOTE_URL.getMessage (), e);
        }
    }

    private void setDriverSize (final WebSetting webSetting) {
        if (this.applicationType == ApplicationType.WEB) {
            final var window = getSession ().getDriver ()
                .manage ()
                .window ();
            switch (webSetting.getResize ()) {
                case CUSTOM:
                    window.setSize (webSetting.getCustomSize ());
                    break;
                case FULL_SCREEN:
                    window.fullscreen ();
                    break;
                case MAXIMIZED:
                    window.maximize ();
                    break;
                case MINIMIZED:
                    window.minimize ();
                    break;
                case NORMAL:
                default:
                    break;
            }
        }
    }

    private WebDriver setupChromeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        chromedriver ().setup ();
        final var options = new ChromeOptions ();
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-gpu");
        options.addArguments ("--disable-dev-shm-usage");
        options.setHeadless (webSetting.isHeadless ());
        return LOGGER.traceExit (new ChromeDriver (options));
    }

    private void setupDriver () {
        LOGGER.traceEntry ();
        if (this.applicationType == ApplicationType.WEB) {
            final var webSetting = this.setting.getUi ()
                .getWebSetting (this.driverKey);
            setupWebDriver (webSetting);
        }
        LOGGER.traceExit ();
    }

    private WebDriver setupEdgeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        edgedriver ().setup ();
        final var options = new EdgeOptions ();
        options.setHeadless (webSetting.isHeadless ());
        return LOGGER.traceExit (new EdgeDriver (options));
    }

    private WebDriver setupFirefoxDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        firefoxdriver ().setup ();
        final var options = new FirefoxOptions ();
        options.setHeadless (webSetting.isHeadless ());
        return LOGGER.traceExit (new FirefoxDriver (options));
    }

    private WebDriver setupOperaDriver () {
        LOGGER.traceEntry ();
        operadriver ().setup ();
        return LOGGER.traceExit (new OperaDriver ());
    }

    private WebDriver setupRemoteDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (new RemoteWebDriver (getRemoteUrl (webSetting), getCapabilities (webSetting)));
    }

    private WebDriver setupSafariDriver () {
        LOGGER.traceEntry ();
        safaridriver ().setup ();
        return LOGGER.traceExit (new SafariDriver ());
    }

    private void setupWebDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        switch (webSetting.getBrowser ()) {
            case CHROME:
                setDriver (this.applicationType, setupChromeDriver (webSetting), this.setting);
                break;
            case NONE:
                throw new FrameworkError (INVALID_BROWSER.getMessage ());
            case REMOTE:
                setDriver (this.applicationType, setupRemoteDriver (webSetting), this.setting);
                break;
            case SAFARI:
                setDriver (this.applicationType, setupSafariDriver (), this.setting);
                break;
            case EDGE:
                setDriver (this.applicationType, setupEdgeDriver (webSetting), this.setting);
                break;
            case FIREFOX:
                setDriver (this.applicationType, setupFirefoxDriver (webSetting), this.setting);
                break;
            case OPERA:
            default:
                setDriver (this.applicationType, setupOperaDriver (), this.setting);
                break;
        }
        setDriverSize (webSetting);
        LOGGER.traceExit ();
    }
}
