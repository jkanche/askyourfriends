<?php 

	$user = $_REQUEST['username'];
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");

	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
	$query = "select * from register where username = '$user';";
    $q=mysql_query("$query");


	while($e=mysql_fetch_assoc($q))
		{
             $value[] = $e;
		}
	mysql_close($con);
	$resultDataJSON = json_encode($value); 
	echo $resultDataJSON;

?> 