
function openForm() {
    document.getElementById("myForm").style.display = "block";
  }
  
  function closeForm() {
    document.getElementById("myForm").style.display = "none";
  }
  
  async function showMeValues(){
  
    let uexpDate = document.getElementById("expDate").value;
    let uexpType = document.getElementById("expType").value;
    let udescription = document.getElementById("description").value;
    let uamount = document.getElementById("amount").value;
    let id = document.getElementById("secretindetifier").value;
     
    console.log(uexpDate);
    console.log(uexpType);
    console.log(udescription);
    console.log(uamount);
  
    let config = {
                expID: 0,
                expDate: uexpDate,
                expType: uexpType,
                description: udescription,
                amount: uamount,
                emplID: id,
                status: "submit"
          };
  
          console.log(config);
  
  
  }
  
  
  async function createExpense(ID){
  
    let uexpDate = document.getElementById("expDate").value;
    let uexpType = document.getElementById("expType").value;
    let udescription = document.getElementById("description").value;
    let uamount = document.getElementById("amount").value;
    let id = document.getElementById("secretindetifier").innerHTML;
     
    let config = {
                expID: 0,
                expDate: uexpDate,
                expType: uexpType,
                description: udescription,
                amount: uamount,
                emplID: id,
                status: "submit"
          };
  
          console.log(config);
  
  
          let request = {
            method: 'POST',
            //credentials: 'include',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(config)
          };
          
          const httpResponse = await fetch(`http://localhost:7000/employees/${ID}/expenses`,request);
          const expense = await httpResponse.json();
          
         
  
          if(expense === null){
            alert("Something went worng!");
          }else{
            alert("You successfully submit your expenes!");
          }

          getExpensesByEmplID(id); 
          
  }