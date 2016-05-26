
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Update Employee Information </title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">OATS Book Tracking System</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!--Side Bar -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="searchBook.html">Search Book<span class="fa arrow"></span></a>
                        </li>
                        <li>
                            <a href="requestForm.html">New Request<span class="fa arrow"></span></a>
                        </li>
                        <li>
                            <a href="searchRequests.jsp">Old Request<span class="fa arrow"></span></a>
                        </li>
                        <li>
                            <a href="../RequestController">Show Requests<span class="fa arrow"></span></a>
                        </li>
                        <li>
                            <a href="employeeUpdate.jsp">Employee Information<span class="fa arrow"></span></a>
                        </li>
                    </ul>
                </div>
                
            </div>
            <!-- /side bar -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header"> Update Employee Details </h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" onsubmit="return searchByEmpName()"  action="../EmployeeUpdateController" method="post">

                                        <div class="form-group">
                                            <label>Employee Name </label>
                                            <input class="form-control" id="empName" placeholder="Search Employee with employee Name" name="emp_Name">
                                            <button type="submit" class="btn btn-default" style = "margin-top:10px" >Search</button>
                                        </div>
                                        </form>
										<%
											String empName = (String)session.getAttribute("empName");
											String empId = (String)session.getAttribute("empId");
											String empEmail = (String)session.getAttribute("emp_email");
											String empStatus = (String)session.getAttribute("emp_status");
											//System.out.println(empName);

											if(empName == null)
											{
												empName = "";
											}
											if(empId == null)
											{
												empId = "";
											}
											if(empStatus == null)
											{
												empStatus = "";
											}
											if(empEmail == null)
											{
												empEmail = "";
											}
										%>
                                        <form role="form" id="updatedelete" onsubmit= "return updateEmployeeDetailsValidation()" action="../EmployeeInfoUpdateController"  method="post">
                                        <div class="form-group">
                                            <label>Employee Id</label>
                                            <input class="form-control" placeholder="Enter employee id" name="empId" value="<%=empId %>">
                                        </div>
                                        <div class="form-group">
                                            <label>Employee Name</label>
                                            <input class="form-control" placeholder="Enter name" name="empName" value="<%=empName %>">
                                        </div>
                                        <div class="form-group">
                                            <label>Email</label>
                                            <input class="form-control" placeholder="Email id" name= "emp_email" value="<%=empEmail %>">
                                        </div>
                                      	    <div class="form-group">
                                            <label>Status</label>
                                            <input class="form-control" placeholder="Active / Inactive" name="emp_Status" value="<%=empStatus %>">
                                        </div>
										<input type="hidden" id="operation" name="operation" value="update">
                                        <button type="button" class="btn btn-default" onclick="updateHidden('update')">Update</button>
                                        <button type="button" class="btn btn-default" onclick="updateHidden('delete')">Delete</button>
                                    </form>
                                </div>                            <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>


<script>
function searchByEmpName(){
	//alert("GOOOOOOOOOOOOOOOOOOOOOOOOre");
	var empName;
  	empName=document.getElementById("empName").value;

	if(empName.length==0){
		alert("Please enter employee id");
	    return false;
	    }
	return true;
}


function updateEmployeeDetailsValidation(){
var empName,empId, empEmail,empStatus;

empId = document.getElementById("empId").value;
empName=document.getElementById("empname").value;
empEmail=document.getElementById("empemail").value;
empStatus=document.getElementById("empstatus").value;

if( empId.length == 0 || empName.length == 0 || empStatus.length == 0 ||empEmail.length == 0)
	{
	alert("Please enter employee id");
    return false;
    }
return true;

}

function updateHidden(op)
{
	document.getElementById("operation").value = op;
	
	document.getElementById("updatedelete").submit();
	
}
</script>

</html>
