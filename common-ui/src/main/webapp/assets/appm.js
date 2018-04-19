/**
 * Created by bingo on 17-7-15.
 */
var selectProject = null;
$(function () {
    var appmOfThis = "数据采集";
    $.ajax({
        url:"/embracebigdata/api/pub/projects",
        method:"get",
        dataType:"json",
        success:function(data){
            var html ="";
            for(var i=0;i<data.length;i++){
                var project = data[i];
                if(i == 0){
                    selectProject(project.id,project.projectName);
                }
                html+='<li><a href="#" onclick="selectProject(\''+project.id+'\',\''+project.projectName+'\')">'+project.projectName+'</a></li><li>';
            }
            $("#projects_list").html(html);
        }
    });
    selectProject = function (projectId,projectName){
        $("#projects_selected").html(projectName);
        loadAppm(projectId);
    }

    function loadAppm(projectId){
        $.ajax({
            url:"/embracebigdata/api/pub/apps/"+projectId,
            method:"get",
            dataType:"json",
            success:function(data){
                var html ="";
                for(var i=0;i<data.length;i++){
                    var appm = data[i];
                    if(appmOfThis == appm.appmName){
                        html+='<li><a target="_blank" href="'+appm.appmUrl+'" class="active">'+appm.appmName+'</a></li>';
                    }else{
                        html+='<li><a target="_blank" href="'+appm.appmUrl+'" >'+appm.appmName+'</a></li>';
                    }

                }
                $("#top_appms").html(html);
            }
        });
    }

})