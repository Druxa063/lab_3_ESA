function loadTable() {
    var xmlhttp, json;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            json = JSON.parse(this.responseText);
            paint(json);
        }
    };
    xmlhttp.open("GET", "/list", true);
    xmlhttp.send();
}

function paint(json) {
    var table, x, txt="", tr;
    table = document.getElementById("tableEmployeeBody");
    table.innerHTML = "";
    for (x in json) {

        tr = document.createElement("tr");
        var tdEmpno = document.createElement("td");
        tdEmpno.innerText = json[x].empno;
        tr.appendChild(tdEmpno);
        var tdEname = document.createElement("td");
        tdEname.innerText = json[x].ename;
        tr.appendChild(tdEname);
        var tdDeptno = document.createElement("td");
        tdDeptno.innerText = json[x].dept.deptno;
        tr.appendChild(tdDeptno);
        var tdDeptname = document.createElement("td");
        tdDeptname.innerText = json[x].dept.dname;
        tr.appendChild(tdDeptname);
        var tdDeptloc = document.createElement("td");
        tdDeptloc.innerText = json[x].dept.loc;
        tr.appendChild(tdDeptloc);

        var tdDelete = document.createElement("td");
        var deleteBtn = document.createElement("button");
        deleteBtn.setAttribute("onclick", "deleteRow(" + json[x].empno + ")");
        deleteBtn.innerText = "Delete";
        tdDelete.appendChild(deleteBtn);
        tr.appendChild(tdDelete);
        var tdUpdate = document.createElement("td");
        var updateBtn = document.createElement("button");
        updateBtn.setAttribute("onclick", "updateRow(" + json[x].empno + ")");
        updateBtn.innerText = "Update";
        tdUpdate.appendChild(updateBtn);
        tr.appendChild(tdUpdate);
        table.appendChild(tr);
    }
}

function deleteRow(id) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            loadTable();
        }
    };
    xmlhttp.open("DELETE", "/delete/" + id, true);
    xmlhttp.send();
}

function updateRow(id) {
    var json;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            json = JSON.parse(this.responseText);
            var form = document.forms["saveForm"];
            form["empno"].value = json[0].empno;
            form["ename"].value = json[0].ename;
            form["deptno"].value = json[0].dept.deptno;
        }
    };
    xmlhttp.open("GET", "/get/" + id, true);
    xmlhttp.send();
}

function save() {
    var form = document.forms["saveForm"];
    var employee = {};
    employee.empno = form["empno"].value;
    employee.ename = form["ename"].value;
    employee.dept = {};
    employee.dept.deptno = form["deptno"].value;
    form["empno"].value = 0;
    form["ename"].value = "";
    form["deptno"].value = 0;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200 || this.status == 201) {
            loadTable();
        }
    };
    xmlhttp.open("POST", "/save", true);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(employee));
}

function search() {
    var xmlhttp, json, empno;
    empno = document.getElementById("empnoSearch");
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            json = JSON.parse(this.responseText);
            paint(json);
        }
    };
    xmlhttp.open("GET", "/get/" + empno.value, true);
    xmlhttp.send();
}
