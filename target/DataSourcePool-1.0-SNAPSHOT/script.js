/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function criaAjaxGet(url,dados,funcao)
{
    let ajax=new XMLHttpRequest();
    ajax.onreadystatechange=funcao;
    ajax.open("GET",url+"?"+dados,true);
    ajax.send();
}

function mostrar()
{
    if(this.readyState==4&&this.status==200)
    {
        let pessoas=this.responseXML.documentElement.getElementsByTagName("pessoa");
        let texto="<table class='table table-striped'><thead class='thead-dark'><tr><th>Código</th><th>Nome</th><th>Idade</th></tr></thead>"
        for(let pessoa of pessoas)
        {
            let codigo=pessoa.getElementsByTagName("codigo")[0].firstChild.nodeValue;
            let idade=pessoa.getElementsByTagName("idade")[0].firstChild.nodeValue;
            let nome=pessoa.getElementsByTagName("nome")[0].firstChild.nodeValue;
            texto+=`<tr><td>${codigo}</td><td>${nome}</td><td>${idade}</td></tr>`
        }
        texto+="</table>"
        document.getElementById("relatorio").innerHTML=texto;
    }
}

onload=()=>{criaAjaxGet("controller","",mostrar);};


