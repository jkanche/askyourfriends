<?php 

	$user = $_REQUEST['username'];
	$pass = $_REQUEST['password'];
	$uname = $_REQUEST['name'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
    $q=mysql_query("UPDATE  `register` SET  `name` =  '$uname', `password` = '$pass' WHERE CONVERT(  `username` USING utf8 ) =  '$user' LIMIT 1 ;");
	
	$output = array('reply' => $q);
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;

?> 