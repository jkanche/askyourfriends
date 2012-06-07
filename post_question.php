<?php 

	$user = $_REQUEST['username'];
	$grpid = $_REQUEST['grp_id'];
	$que = $_REQUEST['grp_quest'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	 mysql_select_db("db364548356",$con);
	 
	//$query = "select * from friends where groupid = ".$grpid.";";
	//$q=mysql_query("$query");
	
	/*while($e=mysql_fetch_assoc($q))
	{
             $value[] = $e['friend_name'];
	}
	
	$f=0;
	foreach ($value as &$name) 
	{
		$query = "INSERT INTO `questions` ( `q_id` , `username` , `group_id` , `quest_body` , `friend_username` , `ans_count` , `date` ) VALUES ('', '$user', '$grpid', '$que', '$name', NULL , NULL);";
		$q=mysql_query("$query");
	}             */
   
	$query = "INSERT INTO `questions` ( `q_id` , `username` , `group_id` , `quest_body` , `friend_username` , `ans_count` , `date` ) VALUES ('', '$user', '$grpid', '$que', 'NULL', NULL , NULL);";
	$q=mysql_query("$query");
	
	$output = array('reply' => $q);
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;

?> 