<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<link href="https://ton.twitter.com/peacock/cdn/d03dcccb06195ca1b00b11cab6fb338585b8d05b/base/application-7992c9ef6fbda2fc9d64f345d9b0d397.css" media="screen" rel="stylesheet" type="text/css" />
<link href="https://ton.twitter.com/peacock/cdn/d03dcccb06195ca1b00b11cab6fb338585b8d05b/peacock/payments-c41fa3887b74690d7dbabb4f2c5693a0.css" media="screen" rel="stylesheet" type="text/css" />

<html>

<head>

    <jsp:include page="jsp_packets/head.jsp"/>
    <title>Ανανέωση Συνδρομής</title>



</head>

<style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
</style>
<body>

<jsp:include page="jsp_packets/providerHeader.jsp"/>

<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Τρίμηνη Ανανέωση Συνδρομής</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">
                </br>
               <form name="form1" id="form-id" action="#">
                <div class="row">
                    <label class="col-md-4 control-label" style="float:left;" for="Card">Card Number</label>
                    <div class="col-md-4">
                        <input id="Card"  type="text" name="text1" placeholder="" class="form-control input-md" required="">
                        <span class="help-block">Εισάγετε το 16ψήφιο ID της κάρτας.</span></br>
                    </div>
                </div>
               </form>

                <div class="row">
                    <label class="col-md-4 control-label" style="float:left;">Full Name</label>
                    <div class="col-md-4">
                        <input  type="text"  class="form-control input-md" required="">
                        <span class="help-block">Εισάγετε το όνομα όπως εμφανίζεται στη κάρτα.</span></br></br>
                    </div>

                <div class="row">
                    <label class="col-md-4 control-label" >Expiration Date</label>
                    <div class="form-group col-md-2" >
                        <select class="form-control">
                            <option value="Month">Month</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                    </div>

                    <div class = "form-group col-md-2">
                        <select id="YY" name="Year" class="form-control">
                            <option value="Year">Year</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                            <option value="2025">2025</option>
                            <option value="2026">2026</option>
                            <option value="2027">2027</option>
                            <option value="2028">2028</option>
                            <option value="2029">2029</option>
                        </select>
                    </div>
                </div>

                    </br></br></br></br></br></br>

                    <div class="row">
                        <label class="col-md-5 control-label" style="float:left;">Security Code</label>
                        <div class="col-md-2">
                            <input id="" name="CVV" type="text" placeholder="" class="form-control input-md" required=""></br></br></br></br>
                        </div></div>


                <div class="row">
                    <label class="col-md-4 control-label" ></label>
                    <div class="col-md-4">

                        <input class="btn btn-default" type="submit" name="submit" value="ΥΠΟΒΟΛΗ" onclick="validateCard(document.form1.text1)"/>

                        </br></br>
                    </div>
                </div>




            </div>

        </div>
    </div>
</div>

            <script>
                function validateCard(inputtxt)
                {


                    var cardnum = /^\d{16}$/;
                    if (inputtxt.value.match(cardnum))
                    {

                        alert("Έγκυρος αριθμός κάρτας.");
                        $("#form-id").submit();
                        window.location.replace("https://localhost:8443/provider/therealdeal");
                        return true;
                    }
                    else{

                        alert("Μη έγκυρος αριθμός κάρτας.");
                        window.location.replace("https://localhost:8443/provider/subcscribe");
                        return false;
                    }

                }


            </script>



<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
