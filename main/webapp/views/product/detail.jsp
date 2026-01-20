<%@ page contentType="text/html; charset=UTF-8" %>

<h2>${product.name}</h2>

<img src="${product.image}" width="200">
<p>${product.description}</p>

<p>Views: ${product.views}</p>

<hr>

<a href="${pageContext.request.contextPath}/favorite?id=${product.productId}">
    ❤️ Like
</a>

&nbsp; | &nbsp;

<a href="${pageContext.request.contextPath}/share?id=${product.productId}">
    📤 Share
</a>

<br><br>
<a href="${pageContext.request.contextPath}/products">⬅ Quay lại</a>
