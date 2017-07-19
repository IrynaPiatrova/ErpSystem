<script type="text/javascript">
    var development = Array('junior developer', 'middle developer', 'senior developer');
    var testing = Array('QAEngineer');
    var design = Array('designer');

    function showPositions(value) {
        var mas = eval(value);
        var el = document.getElementById('positions');
        while (el.childNodes.length > 0) {
            el.removeChild(el.childNodes[el.childNodes.length - 1]);
        }
        var optDis = document.createElement("option");
        optDis.text = "${profilePositionChoose}";
        optDis.disabled = true;
        optDis.selected = true;
        el.appendChild(optDis);
        for (var i = 0; i < mas.length; i++) {
            var opt = document.createElement("option");
            opt.innerHTML = mas[i];
            el.appendChild(opt);
        }
    }
</script>
