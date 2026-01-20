<%@ page contentType="text/html; charset=UTF-8" %>

<h2>Share sản phẩm</h2>

<p><b>${product.name}</b></p>

<form action="${pageContext.request.contextPath}/share" method="post">
    <input type="hidden" name="productId" value="${product.productId}">

    <label>Email người nhận (cách nhau bằng dấu ,)</label><br>
    <textarea name="emails" rows="4" cols="40"></textarea><br><br>

    <button type="submit">Share</button>
</form>
