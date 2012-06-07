<?php 

	$user = $_REQUEST['username'];
	$frd = $_REQUEST['friend'];
	$id = $_REQUEST['grpid'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	$f = 0;
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("db364548356",$con);
	
	$qq = mysql_query("select * from friends where friend_name = '$frd' AND username = '$user';");
	$count = mysql_numrows($qq);
	if($count == 1)
		$q=mysql_query("INSERT INTO  `friends` (  `friend_id` ,  `username` ,  `friend_name` ,  `groupid` ) VALUES ('',  '$user',  '$frd', '$id');");
	
	$output = array('reply' => $q);
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;

?> 