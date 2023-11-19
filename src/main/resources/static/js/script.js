var selectCodeBuilding = document.getElementById("CodeBuilding_filter");
var table = document.getElementById('hospitalDepartments-table');
document.addEventListener("DOMContentLoaded", () => {
    InitOptions();
});

$(document).ready(function() {
    $("#emailForm").submit(function(event) {
        event.preventDefault();
        var formData = $(this).serialize(); // Получаем данные формы
        $.ajax({
            type: "POST",
            url: "/email/send", // Путь к обработчику на сервере
            data: formData, // Данные для отправки
            success: function(response) {
                $("#result").text(response); // Обновляем содержимое элемента с id="result"
            },
            error: function(xhr, status, error) {
                $("#result").text("Error occurred while sending email."); // В случае ошибки
            }
        });
    });
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

// For filtering by name
function tableSearch() {
    var phrase = document.getElementById('search-text');
    var table = document.getElementById('hospitalDepartments-table');
    var regPhrase = new RegExp(phrase.value, 'i');
    var flag = false;

    for (var i = 1; i < table.rows.length; i++) {
        flag = regPhrase.test(table.rows[i].cells[0].innerHTML);
        if (flag) table.rows[i].style.display = "";
        else table.rows[i].style.display = "none";
    }
}

function sortTableByInt(col) {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("hospitalDepartments-table");
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

function filterByCodeBuilding() {
    if (selectCodeBuilding.value !== "all") {
        var table = document.getElementById('hospitalDepartments-table');
        for (var i = 1; i < table.rows.length; i++) {
            if (table.rows[i].cells[2].innerHTML === selectCodeBuilding.value) {
                table.rows[i].style.display = "";
            } else {
                table.rows[i].style.display = "none";
            }
        }
    } else {
        var table = document.getElementById('hospitalDepartments-table');
        for (var i = 1; i < table.rows.length; i++) {
            table.rows[i].style.display = "";
        }
    }
}