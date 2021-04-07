       <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
       <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
       <!DOCTYPE html>
       <html lang="pt-BR">
       <head>
           <meta charset="UTF-8">
           <title>Page</title>
           <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
       </head>
       <body>
       <div class="container">
       <nav class="navbar navbar-expand-lg navbar-light bg-light">
         <div class="container-fluid">
           <a class="navbar-brand" href="#">PlanningHelper</a>
           <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
           </button>
           <div class="collapse navbar-collapse" id="navbarNav">
             <ul class="navbar-nav">
               <li class="nav-item">
                 <a class="nav-link active" aria-current="page" href="/task">Cadastro de issues</a>
               </li>
               <li class="nav-item">
                 <a class="nav-link" href="/showList">Lista de issues</a>
               </li>
             </ul>
           </div>
         </div>
       </nav>
       <br>
       <br>
       <c:if test="${not empty mensagemExcel}">
            <div class="alert alert-warning" role="alert">
               	<h4>${mensagemExcel}</h4>
            </div>
       </c:if>
       <form:form action="/addExcel" modelAttribute="excel" method="post">
       <div class="col">
              <form:label path="caminho" for="exampleFormControlInput1" class="form-label">Caminho(Excel):</form:label>
              <form:input type="text" name="caminho" path="caminho" class="form-control" id="exampleFormControlInput1"/>
       </div>
       <div class="form-group"><br>
             <form:button type="submit" class="btn btn-secondary">ADICIONAR LISTA NO EXCEL</form:button>
       </div>
       </form:form>
       <br>
       <table class="table table-striped">
         <thead>
           <tr>
             <th data-field="issueType" scope="col">IssueType</th>
             <th data-field="title"scope="col">Summary</th>
             <th data-field="description"scope="col">Description</th>
           </tr>
         </thead>
         <tbody>
           <c:forEach items="${tasks}" var="task">
           						<tr>
           							<td>${task.issueType}</td>
           							<td>${task.title}</td>
           							<td>${task.description}</td>
           							</td>
           						</tr>
           					</c:forEach>
         </tbody>
       </table>
       </div>
           <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf "crossorigin="anonymous"></script>
           <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
           <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
       </body>
       </html>