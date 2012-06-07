<?php 

	$user = $_REQUEST['username'];
	
	$user = "vinay";
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");

	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
	$query = "select * from questions where username = '".$user."';";
    $q=mysql_query("$query");
	$count=mysql_numrows($q);
    echo $query;

	if($count == 0 )
	{
	}
	else
	{
		while($e=mysql_fetch_assoc($q))
		{
			echo $e;
             $value[] = $e;
		}
	}
	echo "=============================================";
	$query = "select * from questions where group_id IN (select groupid from friends where friend_name = '$user');";
	$q=mysql_query("$query");
	$count=mysql_numrows($q);
	echo $query;
	
	if($count == 0 )
	{
	}
	else
	{
		
		while($e=mysql_fetch_assoc($q))
		{
			echo $e;
             $value[] = $e;
		}
	}
	
	mysql_close($con);
	$resultDataJSON = json_encode($value); 
	echo $resultDataJSON;

?> 