<script type="text/javascript">
    $(document).ready(function () {
        $(document).on('click', '#requestButton', function () {
            document.getElementById("requestButton").textContent = "${labelCreateRequestChoose}";
            document.getElementById("sickLeaveButton").type = "button";
            document.getElementById("vacationButton").type = "button";
            document.getElementById("sickLeaveTR").style.display = 'none';
            document.getElementById("vacationTR").style.display = 'none';
        });
        $(document).on('click', '#sickLeaveButton', function () {
            document.getElementById("vacationButton").type = "hidden";
            document.getElementById("sickLeaveTR").style.display = 'table';
            document.getElementById("requestSickLeaveButton").type = "submit";
        });
        $(document).on('click', '#vacationButton', function () {
            document.getElementById("sickLeaveButton").type = "hidden";
            document.getElementById("vacationTR").style.display = 'table';
            document.getElementById("requestVacationButton").type = "submit";
        });
        $(document).on('click', '#requestSickLeaveButton', function () {
            document.getElementById("form2").parentNode.removeChild(document.getElementById("form2"));
        });
        $(document).on('click', '#requestVacationButton', function () {
            document.getElementById("form1").parentNode.removeChild(document.getElementById("form1"));
        });
        $(document).on('click', '.rowLink', function () {
            var choosedId = $(this).find('td.choosedId').html();
            document.getElementById("idTimeVacation").value = choosedId;
            document.getElementById("acceptButton").type = "submit";
            document.getElementById("rejectButton").type = "submit";
        });
        $(document).on('click', '#acceptButton', function () {
            document.getElementById("choice").value = "accept";
        });
        $(document).on('click', '#rejectButton', function () {
            document.getElementById("choice").value = "reject";
        });
    })
</script>
