"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[3089],{911:function(e,t,a){a.d(t,{Z:function(){return Z}});var r=a(2706),l=a(7612),n=a(2238),i=a(5373),o=a(955),s=a(8935),m=a(5891),c="sidebar_RWnc",u="sidebarItemTitle_gCko",d="sidebarItemList_iJne",g="sidebarItem_BrIL",p="sidebarItemLink_sXqG",h="sidebarItemLinkActive_bx4j";function b(e){var t=e.sidebar;return l.createElement("aside",{className:"col col--3"},l.createElement("nav",{className:(0,n.Z)(c,"thin-scrollbar"),"aria-label":(0,m.I)({id:"theme.blog.sidebar.navAriaLabel",message:"Blog recent posts navigation",description:"The ARIA label for recent posts in the blog sidebar"})},l.createElement("div",{className:(0,n.Z)(u,"margin-bottom--md")},t.title),l.createElement("ul",{className:(0,n.Z)(d,"clean-list")},t.items.map((function(e){return l.createElement("li",{key:e.permalink,className:g},l.createElement(s.Z,{isNavLink:!0,to:e.permalink,className:p,activeClassName:h},e.title))})))))}var v=a(2338);function E(e){var t=e.sidebar;return l.createElement("ul",{className:"menu__list"},t.items.map((function(e){return l.createElement("li",{key:e.permalink,className:"menu__list-item"},l.createElement(s.Z,{isNavLink:!0,to:e.permalink,className:"menu__link",activeClassName:"menu__link--active"},e.title))})))}function f(e){return l.createElement(v.Zo,{component:E,props:e})}function N(e){var t=e.sidebar,a=(0,o.i)();return null!=t&&t.items.length?"mobile"===a?l.createElement(f,{sidebar:t}):l.createElement(b,{sidebar:t}):null}var k=["sidebar","toc","children"];function Z(e){var t=e.sidebar,a=e.toc,o=e.children,s=(0,r.Z)(e,k),m=t&&t.items.length>0;return l.createElement(i.Z,s,l.createElement("div",{className:"container margin-vert--lg"},l.createElement("div",{className:"row"},l.createElement(N,{sidebar:t}),l.createElement("main",{className:(0,n.Z)("col",{"col--7":m,"col--9 col--offset-1":!m}),itemScope:!0,itemType:"http://schema.org/Blog"},o),a&&l.createElement("div",{className:"col col--2"},a))))}},3019:function(e,t,a){a.r(t),a.d(t,{default:function(){return b}});var r=a(7612),l=a(2238),n=a(7188),i=a(6015),o=a(8005),s=a(911),m=a(1764),c=a(5891),u=a(809);function d(e){var t=e.metadata,a=t.previousPage,l=t.nextPage;return r.createElement("nav",{className:"pagination-nav","aria-label":(0,c.I)({id:"theme.blog.paginator.navAriaLabel",message:"Blog list page navigation",description:"The ARIA label for the blog pagination"})},a&&r.createElement(u.Z,{permalink:a,title:r.createElement(c.Z,{id:"theme.blog.paginator.newerEntries",description:"The label used to navigate to the newer blog posts page (previous page)"},"Newer Entries")}),l&&r.createElement(u.Z,{permalink:l,title:r.createElement(c.Z,{id:"theme.blog.paginator.olderEntries",description:"The label used to navigate to the older blog posts page (next page)"},"Older Entries"),isNext:!0}))}var g=a(1835);function p(e){var t=e.metadata,a=(0,n.Z)().siteConfig.title,l=t.blogDescription,o=t.blogTitle,s="/"===t.permalink?a:o;return r.createElement(r.Fragment,null,r.createElement(i.d,{title:s,description:l}),r.createElement(g.Z,{tag:"blog_posts_list"}))}function h(e){var t=e.metadata,a=e.items,l=e.sidebar;return r.createElement(s.Z,{sidebar:l},a.map((function(e){var t=e.content;return r.createElement(m.Z,{key:t.metadata.permalink,frontMatter:t.frontMatter,assets:t.assets,metadata:t.metadata,truncated:t.metadata.truncated},r.createElement(t,null))})),r.createElement(d,{metadata:t}))}function b(e){return r.createElement(i.FG,{className:(0,l.Z)(o.k.wrapper.blogPages,o.k.page.blogListPage)},r.createElement(p,e),r.createElement(h,e))}},1764:function(e,t,a){a.d(t,{Z:function(){return I}});var r=a(7612),l=a(2238),n=a(5891),i=a(8935),o=a(9797),s=a(7188),m=["zero","one","two","few","many","other"];function c(e){return m.filter((function(t){return e.includes(t)}))}var u={locale:"en",pluralForms:c(["one","other"]),select:function(e){return 1===e?"one":"other"}};function d(){var e=(0,s.Z)().i18n.currentLocale;return(0,r.useMemo)((function(){try{return t=e,a=new Intl.PluralRules(t),{locale:t,pluralForms:c(a.resolvedOptions().pluralCategories),select:function(e){return a.select(e)}}}catch(r){return console.error('Failed to use Intl.PluralRules for locale "'+e+'".\nDocusaurus will fallback to the default (English) implementation.\nError: '+r.message+"\n"),u}var t,a}),[e])}function g(){var e=d();return{selectMessage:function(t,a){return function(e,t,a){var r=e.split("|");if(1===r.length)return r[0];r.length>a.pluralForms.length&&console.error("For locale="+a.locale+", a maximum of "+a.pluralForms.length+" plural forms are expected ("+a.pluralForms.join(",")+"), but the message contains "+r.length+": "+e);var l=a.select(t),n=a.pluralForms.indexOf(l);return r[Math.min(n,r.length-1)]}(a,t,e)}}}var p=a(6698),h=a(1845),b=a(2518),v=a(2589);function E(e){return e.href?r.createElement(i.Z,e):r.createElement(r.Fragment,null,e.children)}function f(e){var t=e.author,a=t.name,l=t.title,n=t.url,i=t.imageURL,o=t.email,s=n||o&&"mailto:"+o||void 0;return r.createElement("div",{className:"avatar margin-bottom--sm"},i&&r.createElement(E,{href:s,className:"avatar__photo-link"},r.createElement("img",{className:"avatar__photo",src:i,alt:a})),a&&r.createElement("div",{className:"avatar__intro",itemProp:"author",itemScope:!0,itemType:"https://schema.org/Person"},r.createElement("div",{className:"avatar__name"},r.createElement(E,{href:s,itemProp:"url"},r.createElement("span",{itemProp:"name"},a))),l&&r.createElement("small",{className:"avatar__subtitle",itemProp:"description"},l)))}var N="authorCol_kNQJ",k="imageOnlyAuthorRow_WqQk",Z="imageOnlyAuthorCol_tRkf";function _(e){var t=e.authors,a=e.assets;if(0===t.length)return null;var n=t.every((function(e){return!e.name}));return r.createElement("div",{className:(0,l.Z)("margin-top--md margin-bottom--sm",n?k:"row")},t.map((function(e,t){var i;return r.createElement("div",{className:(0,l.Z)(!n&&"col col--6",n?Z:N),key:t},r.createElement(f,{author:Object.assign({},e,{imageURL:null!=(i=a.authorsImageUrls[t])?i:e.imageURL})}))})))}var P="blogPostTitle_7lA6",w="blogPostData_OaHK",T="blogPostDetailsFull_HBiB";function I(e){var t,a,s=(a=g().selectMessage,function(e){var t=Math.ceil(e);return a(t,(0,n.I)({id:"theme.blog.post.readingTime.plurals",description:'Pluralized label for "{readingTime} min read". Use as much plural forms (separated by "|") as your language support (see https://www.unicode.org/cldr/cldr-aux/charts/34/supplemental/language_plural_rules.html)',message:"One min read|{readingTime} min read"},{readingTime:t}))}),m=(0,o.C)().withBaseUrl,c=e.children,u=e.frontMatter,d=e.assets,E=e.metadata,f=e.truncated,N=e.isBlogPostPage,k=void 0!==N&&N,Z=E.date,I=E.formattedDate,y=E.permalink,L=E.tags,F=E.readingTime,R=E.title,x=E.editUrl,A=E.authors,C=null!=(t=d.image)?t:u.image,M=!k&&f,B=L.length>0,O=k?"h1":"h2";return r.createElement("article",{className:k?void 0:"margin-bottom--xl",itemProp:"blogPost",itemScope:!0,itemType:"http://schema.org/BlogPosting"},r.createElement("header",null,r.createElement(O,{className:P,itemProp:"headline"},k?R:r.createElement(i.Z,{itemProp:"url",to:y},R)),r.createElement("div",{className:(0,l.Z)(w,"margin-vert--md")},r.createElement("time",{dateTime:Z,itemProp:"datePublished"},I),void 0!==F&&r.createElement(r.Fragment,null," \xb7 ",s(F))),r.createElement(_,{authors:A,assets:d})),C&&r.createElement("meta",{itemProp:"image",content:m(C,{absolute:!0})}),r.createElement("div",{id:k?p.blogPostContainerID:void 0,className:"markdown",itemProp:"articleBody"},r.createElement(h.Z,null,c)),(B||f)&&r.createElement("footer",{className:(0,l.Z)("row docusaurus-mt-lg",k&&T)},B&&r.createElement("div",{className:(0,l.Z)("col",{"col--9":M})},r.createElement(v.Z,{tags:L})),k&&x&&r.createElement("div",{className:"col margin-top--sm"},r.createElement(b.Z,{editUrl:x})),M&&r.createElement("div",{className:(0,l.Z)("col text--right",{"col--3":B})},r.createElement(i.Z,{to:E.permalink,"aria-label":(0,n.I)({message:"Read more about {title}",id:"theme.blog.post.readMoreLabel",description:"The ARIA label for the link to full blog posts from excerpts"},{title:R})},r.createElement("b",null,r.createElement(n.Z,{id:"theme.blog.post.readMore",description:"The label used in blog post item excerpts to link to full blog posts"},"Read More"))))))}}}]);