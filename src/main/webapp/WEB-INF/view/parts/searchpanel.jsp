<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<form method="post" >
  <nav class="navbar navbar-expand-lg navbar-dark static-top bg-dark">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <div class="input-group md-form form-sm form-2 pl-0">
                      <input class="form-control my-0 py-1 lime-border" name="book-name" type="text" placeholder="${msg:getMessage("search-book-name")}" aria-label="Search">
                    </div>
                </li>
                <li class="nav-item active">
                    <div class="input-group md-form form-sm form-2 pl-0">
                    <input class="form-control my-0 py-1 lime-border" name="author" type="text" placeholder="${msg:getMessage("search-author")}" aria-label="Search">
                    </div>
                </li>
                <li class="nav-item active">
                    <div class="input-group md-form form-sm form-2 pl-0">
                    <input class="form-control my-0 py-1 lime-border" name="tags" type="text" placeholder="${msg:getMessage("search-tags")}" aria-label="Search">
                    </div>
                </li>
                <li class="nav-item active">
                    <div class="input-group md-form form-sm form-2 pl-0">
                    <button type="submit" name="search">${msg:getMessage("search-search-button")}</button>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</form>
