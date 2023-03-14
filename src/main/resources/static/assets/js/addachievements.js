 $(document).ready(function () {
  $('#hiddenform').hide();
  $('#hiddenform2').hide();
  $('#hiddenform3').hide();
                 $('#example').DataTable({
                    dom: 'Bfrtip',
                    buttons: [
                       // 'copy',
                         'excel',
                         'pdf',
                         'pageLength'
                         ]
                });

                $( "#kyrteng" ).on('change keyup paste', function() {
                     $('#kyrteng').css('border', '1px solid #ced4da');

                   });


                   $( "#achievement" ).on('change', function() {
                    $('#hiddenform2').hide();
                    $('#hiddenform').hide();
                    $('#hiddenform3').hide();
                                    if($('#achievement').val()!=-1){
                                       
                                        $('#hiddenform').show();
                                        $('#hiddenform3').show();
                                        if($('#achievement').val()==2){
                                            $('#hiddenform2').show();
                                            $('#hiddenform3').hide();
                                        }
                                        
                                    }
                                       

                    });

            });


        function validate() {
                // validation code
               //  alert("validation");
                // if($('#achievement').val()==2){

                    
                // }


                if($('#kyrteng').val()==''||$('#achievement').val()==-1){
                    alert("Please fill the highlighted field")
                    $('#kyrteng').css('border', 'solid 1px red');
                    $('#kyrteng').focus();
                }
                else if($('#dorbar').val()==''||$('#achievement').val()==-1){
                     alert("Please fill the highlighted field")
                     $('#dorbar').css('border', 'solid 1px red');
                     $('#dorbar').focus();
                }
                else if($('#date').val()==''||$('#achievement').val()==-1){
                     alert("Please fill the highlighted field")
                     $('#date').css('border', 'solid 1px red');
                     $('#date').focus();
                 }
             else {

                      if(confirm('Do you really want to submit the form?')){
                      $.ajax({
                    type: "POST",
                    url: "./saveachievements",
                    data: {achievement: $('#achievement').val(),kyrteng: $('#kyrteng').val(), field: $('#field').val(), 
                    details: $('#details').val(), dorbar: $('#dorbar').val(), date: $('#date').val(),
                     venue: $('#venue').val(),rank: $('#rank').val(),jingpule: $('#jingpule').val()
                    
                    },

                   // beforeSend: function (xhr)
                   // {
                  //      xhr.setRequestHeader(csrfHeader, csrfToken);
                  //  },
                    success: function (data) {

                        if (data != "-1") {
                            alert("Data Saved Successfully");
                            location.reload();
                        } else {
                            alert("Sorry, Data was Not Saved, Please try again. ");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                        alert("error:" + textStatus + " - exception:" + errorThrown);
                    }
                      });

                      }else{
                                alert('Data was Not Saved');

                      }


             }


}


