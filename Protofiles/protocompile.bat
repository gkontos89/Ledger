SET proto_path=%~dp0
SET src_dir=%proto_path%
SET dst_dir=%proto_path%
SET proto_file_name=heartbeat.proto
SET proto_file=%proto_path%\%proto_file_name%
protoc -I=%src_dir% --java_out=%dst_dir% %proto_file%

REM Add where proto file output should be copied in the project
SET android_marshmallow_loc=%proto_path%\..\MarshmallowAndroid\app\src\main\java\com\gkontos\kontos
SET proto_java_file=%proto_path%Heartbeat.java

xcopy /Y %proto_java_file% %android_marshmallow_loc%