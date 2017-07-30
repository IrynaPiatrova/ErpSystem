<script type="text/javascript">
    $(document).ready(function () {
        $(document).on('click', '.rowLink', function () {
            var choosedId = $(this).find('td.choosedId').html();
            var choosedNumber = $(this).find('td.choosedNumber').html();
            document.getElementById("idWorker").value = choosedId;
            document.getElementById("editButton").type = "submit";
            document.getElementById("editButton").value = "${labelEditWorkerButton}" + choosedNumber;
            document.getElementById("idWorkerToShow").value = choosedId;
            document.getElementById("editButtonToShow").type = "submit";
            document.getElementById("editButtonToShow").value = "${labelShowWorkerButton}" + choosedNumber;
            highlight_Table_Rows("dev-table", "hover_Row", "clicked_Row");
        });
    })

    (function () {
        'use strict';
        var $ = jQuery;
        $.fn.extend({
            filterTable: function () {
                return this.each(function () {
                    $(this).on('keyup', function (e) {
                        $('.filterTable_no_results').remove();
                        var $this = $(this), search = $this.val().toLowerCase(),
                            target = $this.attr('data-filters'), $target = $(target),
                            $rows = $target.find('tbody tr');
                        if (search == '') {
                            $rows.show();
                        } else {
                            $rows.each(function () {
                                var $this = $(this);
                                $this.text().toLowerCase().indexOf(search) === -1 ? $this.hide() : $this.show();
                            })
                            if ($target.find('tbody tr:visible').size() === 0) {
                                var col_count = $target.find('tr').first().find('td').size();
                                var no_results = $('<tr class="filterTable_no_results"><td colspan="' + col_count + '">No results found</td></tr>')
                                $target.find('tbody').append(no_results);
                            }
                        }
                    });
                });
            }
        });
        $('[data-action="filter"]').filterTable();
    })(jQuery);

    $(function () {
        // attach table filter plugin to inputs
        $('[data-action="filter"]').filterTable();

        $('.container').on('click', '.panel-heading span.filter', function (e) {
            var $this = $(this),
                $panel = $this.parents('.panel');

            $panel.find('.panel-body').slideToggle();
            if ($this.css('display') != 'none') {
                $panel.find('.panel-body input').focus();
            }
        });
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>

<script type="text/javascript">
    function highlight_Table_Rows(table_Id, hover_Class, click_Class) {
        var table = document.getElementById(table_Id);

        if (hover_Class) {
            var hover_Class_Reg = new RegExp("\\b" + hover_Class + "\\b");
            table.onmouseover = table.onmouseout = function (e) {
                if (!e) e = window.event;
                var elem = e.target || e.srcElement;
                while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
                    elem = elem.parentNode;

                if (elem.parentNode.tagName == 'TR' &&
                    elem.parentNode.parentNode.tagName == 'TBODY') {
                    var row = elem.parentNode;
                    if (!row.getAttribute('clicked_Row'))
                        row.className = e.type == "mouseover" ? row.className +
                            " " + hover_Class : row.className.replace(hover_Class_Reg, " ");
                }
            };
        }

        if (click_Class) table.onclick = function (e) {
            if (!e) e = window.event;
            var elem = e.target || e.srcElement;
            while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
                elem = elem.parentNode;

            if (elem.parentNode.tagName == 'TR' &&
                elem.parentNode.parentNode.tagName == 'TBODY') {
                var click_Class_Reg = new RegExp("\\b" + click_Class + "\\b");
                var row = elem.parentNode;

                if (!row.getAttribute('clicked_Row')) {
                    if (hover_Class) row.className = row.className.replace(hover_Class_Reg, "");
                    row.className += " " + click_Class;
                    row.setAttribute('clicked_Row', true);

                    var lastRowI = table.getAttribute("last_Clicked_Row");
                    if (lastRowI !== null && lastRowI !== '' && row.sectionRowIndex != lastRowI) {
                        var lastRow = table.tBodies[0].rows[lastRowI];
                        lastRow.className = lastRow.className.replace(click_Class_Reg, "");
                        lastRow.removeAttribute('clicked_Row');
                    }
                    table.setAttribute("last_Clicked_Row", row.sectionRowIndex);
                }
            }
        };
    }
</script>