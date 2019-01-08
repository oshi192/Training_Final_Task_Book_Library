<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<button
    class="btn btn-info"
    type="button"
    data-toggle="modal"
    data-target="#myModal"
    type="submit"
    name="edit"
    value="${Book.id}"
    class="btn btn-success btn-xs"
    data-title="Edit"
>
    <span class="glyphicon glyphicon-pencil"></span>
    ${msg:getMessage("button-edit")}
</button>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">
                    ×
                </button>
                <h4 class="modal-title">${msg:getMessage("edit-book-header")}</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method='POST'>
                 <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">
                    ${msg:getMessage("book-taken-name")}
                  </label>
                  <div class="col-sm-10">
                   <input type="text" name="book" class="form-control" id="pass" placeholder="${Book.name}">
                  </div>
                 </div>
                 <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">
                   ${msg:getMessage("book-taken-section")}
                  </label>
                  <div class="col-sm-10">
                   <input type="text" name="book" class="form-control" id="pass" placeholder="${Book.section}">
                  </div>
                 </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="submit" name="submit" value="${Book.id}" data-dismiss="modal">
                    ${msg:getMessage("edit-book-button-save")}
                </button>
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    ${msg:getMessage("edit-book-button-close")}
                </button>
            </div>
        </div>
    </div>
</div>