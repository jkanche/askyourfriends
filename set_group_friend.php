<?php 

	$user = $_REQUEST['username'];
	$frname = $_REQUEST['fname'];
	$grpid = $_REQUEST['grp_id'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	 mysql_select_db("db364548356",$con);
	 
	$query = "INSERT INTO `friends` ( `friend_id` , `username` , `friend_name` , `groupid` ) VALUES ('', '$user', '$frname', '$grpid');";
	$q=mysql_query("$query");
	
	while($e=mysql_fetch_assoc($q))
	{
             $value[] = $e;
	}
	
	$resultDataJSON = json_encode($value); 
	echo $resultDataJSON;

?> 