<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/
loose.dtd">
<html>
<head>
<title>Modifier Professeur</title>
</head>
<body>

	<form:form method="post" modelAttribute="modification"
		action="modifierProfesseur">
		<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
				<th>Adresse</th>
				<th>Sexe</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${modification.listeProfesseurs}" var="prof" varStatus="status">
				<tr>
					<td><c:out value="${prof.id}" /> <input 
							name="listeProfesseurs[${status.index}].id" value="${prof.id}"  type="hidden" /></td>
					<td><c:out value="${prof.nom}" />
					<input	name="listeProfesseurs[${status.index}].nom" value="${prof.nom}" /></td>
					<td><c:out value="${prof.prenom}" />
					<input 	name="listeProfesseurs[${status.index}].prenom" value="${prof.prenom}" />
					</td>
					<td><c:out value="${prof.date_naissance}" />
					<input	name="listeProfesseurs[${status.index}].date_naissance" value="${prof.date_naissance}" />
					</td>
					<td><c:out value="${prof.adresse}" />
					<input	name="listeProfesseurs[${status.index}].adresse" value="${prof.adresse}" />
					</td>
					<td><c:out value="${prof.sexe}" />
					<input	name="listeProfesseurs[${status.index}].sexe" value="${prof.sexe}" />
					
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<input type="submit" />
	</form:form>
</body>
</html>