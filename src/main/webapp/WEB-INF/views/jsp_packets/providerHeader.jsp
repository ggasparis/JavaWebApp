<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--Navigation & Intro-->
<header>

    <!--Navbar-->
    <nav class="navbar navbar-toggleable-md navbar-dark ">
        <div class="container">
            <button class="navbar-toggler navbar-toggler-right collapsed" type="button" data-toggle="collapse"
                    data-target="#navbarNav1" aria-controls="navbarNav1" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="my-1 mx-2 close" style="color: white;">X</span>
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="/index">
                <!--LOGO-->
                <img class="align-top" style="display: inline" src="${contextPath}/resources/img/rozpanda.png" alt="RozPanda Logo" width="30" height="30">
                <!--/LOGO-->
                <strong>RozPanda</strong>
            </a>
            <div class="collapse navbar-collapse navbar-nav navbar-right col-md-7"></div>

            <div class="collapse navbar-collapse navbar-nav navbar-right col-md-5" style="float:right;">

            <ul class="nav navbar-nav">

                <li class="nav-item">
                    <a class="nav-link" href="/provider/showMonthlyReports">Μηνιαία Αναφορά</a>
                </li>

                <li class="nav-item">
                <a class="nav-link" href="/provider">Πάροχος</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/providerProfile">${user.getUsername()}</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/logout">Αποσύνδεση</a>
                </li>
            </ul>
            </div>
        </div>
    </nav>
    <!--/.Navbar-->
</header>
<!--/Navigation & Intro-->