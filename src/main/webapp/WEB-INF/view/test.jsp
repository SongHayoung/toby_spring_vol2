<spring:bind path="user.name">
    <label for="name">Name : </label>
    <input type="text" id="${status.expression}" name="${status.expression}" value="${status.value}" />
    <span class="errorMessage">
        <c:forEach var="errorMessage" items="${status.errorMessages}">
            ${errorMessages}
        </c:forEach>
     </span>
</spring:bind>