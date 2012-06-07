<?php 

	$qid = $_REQUEST['q_id'];
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");

	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
	$query = "select * from answers where fq_id = ".$qid.";";
    $q=mysql_query("$query");
	$count=mysql_numrows($q);
    mysql_close($con);

	if($count == 0 )
	{
		$value = array("count" => $count);
	}
	else
	{
		while($e=mysql_fetch_assoc($q))
		{
             $value[] = $e;
		}
	}

	$resultDataJSON = json_encode($value); 
	echo $resultDataJSON;

?> 