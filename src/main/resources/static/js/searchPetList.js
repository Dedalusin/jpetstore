var xhr;
function findPet(){
    var name=document.getElementById('name').value;
    xhr=new XMLHttpRequest();
    xhr.onreadystatechange=process;//3
    xhr.open("GET","findPet?name="+name,true);//1
    xhr.send(null);//2
}

function doblur(){
    document.getElementById('context1').style.display="none";
}
function process(){
    if(xhr.readyState==4){
        if(xhr.status==200){
            var res=xhr.responseText;
            var res_split=res.split(",");
            var html="";
            for(var i=0;i<res_split.length&&i<5;i++){
                html+="<div style='line-height: 17px;font-size: 17px;'>"+res_split[i]+"</div>";
            }
            document.getElementById('context1').innerHTML=html;
            document.getElementById('context1').style.display="block";
        }
    }
}