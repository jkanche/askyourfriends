<?php 

	$user = $_REQUEST['username'];
	$qid = $_REQUEST['q_id'];
	$ans = $_REQUEST['ans_body'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	$f = 0;
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
    $q=mysql_query("INSERT INTO answers VALUES ('$qid','$ans', '$user')");
	
	$output = array('reply' => $q);
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;

?> 