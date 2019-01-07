 <body>
    <div class="container-fluid bg">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <div id="log">
                    <form  method="POST" action="${pageContext.request.contextPath}/submit-login">
                    <h1>${msg:getMessage("login-please-login")}</h1>
                    <div id="errormessagesbox">
                        <c:if test='${errorMessage!=""}'>
                            <c:out value="${errorMessage}"/>
                        </c:if>
                    </div>
                    <div id="messagesbox">
                        <c:if test='${message!=""}'>
                            <c:out value="${message}"/>
                        </c:if>
                    </div>
                    <img class="rounded mx-auto d-block img img-responsive img-circle" src="<c:url value="/resources/image/user1.png" />"/>
                        <div class="form-group">
                            <label>${msg:getMessage("login-label-email")}</label>
                            <input name="email" type="email" class="form-control" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label>${msg:getMessage("login-label-password")}</label>
                            <input name="password" type="password" class="form-control" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-success btn-block ">${msg:getMessage("login-button-login")}</button>
                         </form>
                        <a href="${pageContext.request.contextPath}/registration">
                            <button class="btn btn-danger btn-block ">${msg:getMessage("login-button-register")}</button>
                        </a>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
        </div>
    </div>
    </body>