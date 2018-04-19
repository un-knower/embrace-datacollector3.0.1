#!/bin/bash
basepath=$(cd `dirname $0`; pwd)
PROJECT_BASE=$basepath

#configs 
Thread_Size=1
Package_Names=''
Diff_Base=HEAD

#源码所在目录
PROJECT_BASE=~/workspaces/bigdata/embrace-datacollector

echo $PROJECT_BASE

SERVER_NAME=streamsets
OPTION_FILE_NAME=sdc-env.sh


SCRIPT=`basename ${BASH_SOURCE[0]}`

# Set fonts for help
NORM=`tput sgr0`
BOLD=`tput bold`
REV=`tput smso`
script_font="\033[4;31m"
normal_font="\033[0m"
function HELP
{
 echo -e "===============${REV}${BOLD}帮助文档${NORM}==============="
 echo -e "该工具依赖Git、Maven命令，请确保已正确安装"
 echo "可选命令:"
 echo -e "\t-u${NORM}  --自动对变更代码的包打包、发布并重启服务"
 echo -e "\t-p${NORM}  --通过Git diff查找修改过的源码，并进行打包"
 echo -e "\t-a${NORM}  --全量打包"
 echo -e "\t-c${NORM}  --拷贝更新后的jar包至发布目录"
 echo -e "\t-k${NORM}  --关闭服务"
 echo -e "\t-s${NORM}  --启动服务"
 echo -e "\t-r${NORM}  --重启服务"
 echo -e "\t-d${NORM}  --打印修改的源码文件列表"
 echo "可选参数:"
 echo -e "\t-T${NORM}  --打包线程数，如：-T 2"
 echo -e "\t-P${NORM}  --指定节点库名（组件目录名，多个以,分割），如：-P commonlib,hdfs-protolib"
 echo "----------------------------------------------"
 exit 1
}

function CheckDiff() {
	#检查是否有需要重新打包的内容
	cd $PROJECT_BASE
	diff_count=`git diff $Diff_Base --name-only | grep / | awk -F / '{print $1}' | uniq | wc -l`
	if [[ $diff_count -eq 0 ]];
	then	
		echo "None Updated Code, Exit!"
		exit 1
	else
		echo "Show Edited Files"
		git diff $Diff_Base --name-only &
	fi
}

function Package() {
	#对变动的内容打包	
	cd $PROJECT_BASE
	if [ ! "$Package_Names" = "" ]; then
		echo "Start Packing $Package_Names"			
		echo $Package_Names | awk -F , '{for(i=0;++i<=NF;){print $i}}' | uniq | xargs -P 1 -I {} mvn clean package -pl {} -DskipTests -Pdist,ui -DskipRat -T$Thread_Size
	else
		CheckDiff
		is_first=`find dist -type d -name sdc-static-web | wc -l`	
		if [[ $is_first -eq 0 ]]; then
			echo "Having no Packing Before, Go to Packing All"
			PackageAll
		else
			echo "Start Packing (only for edited modules)"			
			git diff $Diff_Base --name-only | grep -v "dist" | grep -v "release"| grep / | awk -F / '{print $1}' | uniq | xargs -P 1 -I {} mvn clean package -pl {} -DskipTests -Pdist,ui -DskipRat -T$Thread_Size
		fi
	fi
}

function AddOptions() {
	cd $PROJECT_BASE	
	echo "Add Option"
	#删除原配置
	find dist/target -type f -name $OPTION_FILE_NAME | xargs sed -i /transport=dt_socket/d
	#增加指定配置
	find dist/target -type f -name $OPTION_FILE_NAME | xargs sed -i '49 a\export SDC_JAVA_OPTS="${SDC_JAVA_OPTS} -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n"'
	#显示添加的配置内容
	find dist/target -type f -name $OPTION_FILE_NAME | xargs grep transport=dt_socket
}

function PackageAll() {
	#全两打包发布
	echo "Start Packing All"
	cd $PROJECT_BASE
	mvn clean package -Pdist,ui -DskipTests -DskipRat -T$Thread_Size
	AddOptions
}

function InstallAll() {
	#全两打包发布
	echo "Start Install All"
	cd $PROJECT_BASE
	#mvn package -Drelease -DskipTests
	mvn clean install -Pdist,ui -DskipTests -DskipRat -T$Thread_Size
	AddOptions
}

function CopyToTarget() {
	#拷贝Jar包至目标目录
	echo "Start Copy Jars to Target Directories "
	cd $PROJECT_BASE
	
	update_pkgs=''
	if [ ! "$Package_Names" = "" ]; then			
		update_pkgs=`echo $Package_Names | awk -F , '{for(i=0;++i<=NF;){print $i}}' | uniq`
	else		
		update_pkgs=`git diff $Diff_Base --name-only | grep -v "dist" | grep -v "release" | grep / | awk -F / '{print $1}' | uniq`
	fi
	echo "Copy Jars in $update_pkgs"
	for update_pkg in $update_pkgs; do
		update_jars=`printf $update_pkg | xargs -I {} find {} -maxdepth 2 -type f -name "*.jar" ! -name "*-tests.jar" ! -name "original-*.jar" ! -name "*-sources.jar"`
		for alpha in $update_jars; do
			jar_name=`echo $alpha | awk -F / '{print $NF}'`
			target_files=`find dist/target/ -type f -name $jar_name`
			for target_file in $target_files; do
				cp -rvf $alpha $target_file
			done
		done
	done
	
	echo "Finish Copy Jars to Target Directories"
	cp_ui=`echo $update_pkgs | grep datacollector-ui | uniq | wc -l`
	
	if [[ ! $cp_ui -eq 0 ]];
	then
		echo "Start Copy UI to Target Directories"
		target_ui=`find dist/target/ -type d -name "sdc-static-web"`
		rm -rf $target_ui/*
		cp -rf datacollector-ui/target/dist/* $target_ui/
		rm -rf $target_ui/app $target_ui/bower_components $target_ui/karma-conf.js
		echo "Finish Copy UI to Target Directories"
	fi	
}

function StopServer() {
	echo "To Stop $SERVER_NAME Server"	
	process_id=`ps -ef | grep $SERVER_NAME | grep -v grep | grep -v idea | grep -v eclipse | awk '{print $2}'`
	#echo $process_id
	if [ ! -n "$process_id" ];
	then
		echo "Server $SERVER_NAME is not Running!"
	else
		ps -ef | grep $SERVER_NAME | grep -v grep | awk '{print $2}' | xargs kill -9
		echo "Stop $SERVER_NAME Server Success!"
	fi
}

function StartServer() {
	cd $PROJECT_BASE
	echo "Start $SERVER_NAME Server"
	process_id=`ps -ef | grep $SERVER_NAME | grep -v grep | grep -v idea | grep -v eclipse | awk '{print $2}'`
	if [ ! -n "$process_id" ];
	then		
		find $PROJECT_BASE/dist/target -type f -name $SERVER_NAME | xargs -I {} nohup sh {} dc &
		tailf nohup.out
	else
		echo "Server $SERVER_NAME is Running!"
	fi
}

function RestartServer() {
	#重启服务
	echo "ReStart $SERVER_NAME Server"
	StopServer
	StartServer
}

function Auto() {
	#升级更新的内容，并重启服务
	Package
	StopServer
	CopyToTarget
	StartServer
}
function AutoForAll() {
	#自动全量打包并重启服务
	StopServer
	PackageAll
	StartServer
}

OPTION=''
while getopts ":uacpkdsrhiT:P:D:" arg #选项后面的冒号表示该选项需要参数
do
        case $arg in
	T) Thread_Size=$OPTARG
		;;
	P) Package_Names=$OPTARG
		;;
	D) Diff_Base=$OPTARG
		;;
	?) OPTION=$arg
		;;
        esac
done

case $OPTION in
	c) CopyToTarget
		exit 1;;
	p) Package
		exit 1;;
	k) StopServer
		exit 1;;
	s) StartServer
		exit 1;;
	r) RestartServer
		exit 1;;
	d) CheckDiff
		exit 1;;
	u) Auto
		exit 1;;
	a) AutoForAll
		exit 1;;
	i) InstallAll
		exit 1;;
	h) HELP
		exit 1;;
	?) #当有不认识的选项的时候arg为?
		exit 1;;
esac

echo -e "打包升级：$script_font""sh $SCRIPT -u$normal_font"
echo -e "部分打包：$script_font""sh $SCRIPT -p$normal_font"
echo -e "拷贝新包：$script_font""sh $SCRIPT -c$normal_font"
echo -e "更多帮助：$script_font""sh $SCRIPT -h$normal_font"

