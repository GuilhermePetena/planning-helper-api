<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
<div class="container">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand"><b>PlanningHelper</b></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/task">Cadastro de task</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/task-list">Lista de task</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<br>
<br>
<c:if test="${not empty mensagemTaskedit}">
        					<div class="alert alert-warning" role="alert">
        						<h4>${mensagemTaskedit}</h4>
        					</div>
        </c:if>
<form:form action="/task-edit" modelAttribute="task" method="PUT">
        <div class="col">
            <form:label for="exampleFormControlSelect1" path="issueType">Issue Type:</form:label>
            <form:select class="form-control" id="exampleFormControlSelect1" path="issueType" name="issueType" type="text" value="${task.issueType}">
              <form:option value="Story">Story</form:option>
              <form:option value="Sub-Development">Sub-Development</form:option>
              <form:option value="Sub-Task">Sub-Task</form:option>
              <form:option value="Sub-Test">Sub-Test</form:option>
              <form:option value="Rituals">Rituals</form:option>
              <form:option value="Leaders">Leaders Activities</form:option>
            </form:select>
          </div>
        <div class="col">
                <form:label path="title" for="exampleFormControlInput1" class="form-label">Summary:</form:label>
                <form:input type="text" name="title" path="title" value="${task.title}" class="form-control" id="exampleFormControlInput1"/>
        </div>
    <div class="form-group">
        <form:label path="description" for="exampleFormControlTextarea1" class="form-label">Description:</form:label>
        <form:textarea type="text" name="description"  value="${task.description}" path="description" class="form-control" id="exampleFormControlTextarea1" rows="3"></form:textarea>
    </div>
    <div class="form-group"><br>
        <form:button type="submit" class="btn btn-primary">ATUALIZAR TASK NA LISTA</form:button>
    </div>
</form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</body>
</html>
