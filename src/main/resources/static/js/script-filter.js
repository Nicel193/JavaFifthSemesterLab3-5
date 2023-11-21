var selectCodeBuilding = document.getElementById("CodeBuilding_filter");
var table = document.getElementById('hospitalDepartments-table');
document.addEventListener("DOMContentLoaded", () => {
    InitOptions();
});

function InitOptions() {
    var uniqueValues = new Set();

    for (var i = 1; i < table.rows.length; i++) {
        AddOption(table.rows[i].cells[2].innerHTML);
    }

    function AddOption(value) {
        if (!uniqueValues.has(value)) {
            var option = document.createElement("option");
            option.value = value;
            option.textContent = value;
            selectCodeBuilding.appendChild(option);
            uniqueValues.add(value);
        }
    }
}

function confirmation() {
    return confirm('Are you sure you want to do this?');
}

function tableSearch() {
    var phrase = document.getElementById('search-text');
    var table = document.getElementById('patientDepartments-table');
    var regPhrase = new RegExp(phrase.value, 'i');
    var flag = false;

    for (var i = 1; i < table.rows.length; i++) {
        flag = regPhrase.test(table.rows[i].cells[1].innerHTML);
        if (flag) table.rows[i].style.display = "";
        else table.rows[i].style.display = "none";
    }
}

function sortTableByInt(col) {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("patientDepartments-table");
    switching = true;

    while (switching) {
        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[col];
            y = rows[i + 1].getElementsByTagName("TD")[col];
            if (parseInt(x.innerHTML) > parseInt(y.innerHTML)) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}