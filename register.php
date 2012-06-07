<?php 

	$user = $_REQUEST['username'];
	$pass = $_REQUEST['password'];
	$name = $_REQUEST['name'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
    $q=mysql_query("INSERT INTO register VALUES ('$user','$pass', '$name')");

	$output = array('reply' => $q);
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;

?> 