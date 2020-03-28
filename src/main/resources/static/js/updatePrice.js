function updateQuantity(){
    var total=0;
    var table=document.getElementById("listOfCart");
    for(var i=1;i<table.rows.length-1;i++){
        var price=table.rows[i].cells[5].innerText;
        var qty=document.getElementsByTagName("input").item(i+1).value;
        var prices=price.split("$");
        table.rows[i].cells[6].innerText="$"+prices[1]*qty;
        total+=prices[1]*qty;
    }
    document.getElementById("totalPrice").innerText="Sub Total:$"+total;
}