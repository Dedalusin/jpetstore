var xhr;
function register() {
    var username = document.getElementById('username').value;
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = process;//3
    xhr.open("GET","usernameIsExist?username="+username,true);//1
    xhr.send(null);//2
}
function process() {
    if (xhr.readyState == 4){
        if (xhr.status == 200){
            var responseInfo = xhr.responseText;
            var msg = document.getElementById('isExistInfo')
            if(responseInfo=='Exist'){
                msg.classList.add('errormsg');
                msg.innerText='Not Available';
            }else if(responseInfo=='Not Exist'){
                msg.classList.add('okmsg');
                msg.innerText='Available';
            }
        }
    }
}