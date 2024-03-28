<%@ page language="java" import="java.util.*" %>
<%@ page import = "java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("application");
	String rzpSecretKey=resource.getString("rzp.secretkey");
%>
  <!-- <button id="rzp-button1">Pay with Razorpay</button> -->
  <!-- <button>razor order id : ${rzpModel.getOrderId()}</button> -->
  <!-- <button>razor rzpAmount: ${rzpModel.getRzpAmount()}</button> -->
  <!-- <button onclick="alertTest()">razor</button> -->
  <title>Razorpay</title>
  <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <!-- <link rel="stylesheet" href="sweetalert2.min.css"> -->
  <script>
  
	

    var options = {						      
      "key": "${rzpSecretKey}", // Enter the Key ID generated from the Dashboard
      "amount": "${rzpModel.getRzpAmount()}", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
      "currency": "INR",
      "name": "Bobcard Limited",
      "description": "Test Transaction",
      "image": "https://1000logos.net/wp-content/uploads/2021/06/Bank-of-Baroda-icon-1536x1024.png",
      "order_id": "${rzpModel.getOrderId()}", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
      "handler": function (response) {
        paymentSuccess(response);
        console.log(response.razorpay_payment_id);
        console.log(response.razorpay_order_id);
        console.log(response.razorpay_signature)
      },
      "modal": {
        "ondismiss": function () {
          // window.location.replace("//put your redirect URL");
          window.open("https://0781-123-108-58-124.ngrok-free.app/wait", "_self")
        }
      },
      "prefill": {
        "name": "",
        "email": "",
        "contact": ""
      },
      "notes": {
        "address": "BobCard Limited"
      },
      "theme": {
        "color": "#eb6d4f"
      },
    };
    var rzp1 = new Razorpay(options);
    console.log("rzp obj", rzp1)
    rzp1.on('payment.failed', function (response) {
      paymentFailure(response);
      console.log(response.error.code);
      console.log(response.error.description);
      console.log(response.error.source);
      console.log(response.error.step);
      console.log(response.error.reason);
      console.log(response.error.metadata.order_id);
      console.log(response.error.metadata.payment_id);
    });
    /*
    document.getElementById('rzp-button1').onclick = function(e){
        rzp1.open();
        e.preventDefault();
    }
    */
    rzp1.open();

    function paymentSuccess(response) {
      console.log("Payment success response", response)

      Swal.fire({
        title: "Success!",
        text: "Payment Success!",
        icon: "success",
        confirmButtonColor: "#eb6d4f",
        confirmButtonText: "ok"
      }).then((result) => {
        // console.log("swal ok result",result);
        if (result.isConfirmed) {
          // console.log("inside confirmed")
          window.open("https://0781-123-108-58-124.ngrok-free.app/wait", "_self")
        }
      });

    };

    function paymentFailure(response) {
      console.log("Payment failure response", response)
      //	rzp1.close();

      /*
      Swal.fire({
        title: "Failed!",
        text: "Payment Failed!",
        icon: "error",
        confirmButtonColor: "#eb6d4f",
        confirmButtonText: "ok"
      }).then((result) => {
        console.log("swal ok result", result);
        if (result.isConfirmed) {
          // console.log("inside confirmed")
          rzp1.close();
          window.open("https://0781-123-108-58-124.ngrok-free.app/wait", "_self")
        }
      });

      */

    }

    function alertTest() {
      Swal.fire({
        text: "Payment Failed!",
        title: "Failed!",
        icon: "error",
        confirmButtonColor: "#eb6d4f",
        confirmButtonText: "OK"
      }).then((result) => {
        console.log("swal ok result", result);

        if (result.isConfirmed) {
          // console.log("inside confirmed")
          window.open("https://0781-123-108-58-124.ngrok-free.app/wait", "_self")
        }
      });
    }

  </script>