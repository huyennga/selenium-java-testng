WinWait("Open", "", "30")
If WinExists("Open") Then
	  Sleep(1500);
	  ControlSetText("Open", "", "Edit1", $CmdLine[1]);
	  Sleep(1500);
      ControlClick("Open", "", "&Open");
EndIf