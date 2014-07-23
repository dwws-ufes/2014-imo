/**
 * Funções específicas da aplicação IMO
 */
function toggleCargo() {
	
	var panelSelect = document.getElementById("form_vaga:panel_select_cargo_vaga");
	var selectCargo = document.getElementById("form_vaga:select_cargo_vaga");
	
	var panelInput = document.getElementById("form_vaga:panel_novo_cargo_vaga");
	var inputCargo = document.getElementById("form_vaga:input_novo_cargo_vaga");
	
	var checkNovoCargo = document.getElementById("form_vaga:check_novo_cargo_vaga");
	
	if (checkNovoCargo.checked) {
		selectCargo.selectedIndex = 0;
		selectCargo.disabled = true;
		panelSelect.style.display = 'none';
		
		panelInput.style.display = '';
		inputCargo.disabled = false;
	}
	else {
		selectCargo.selectedIndex = 0;
		selectCargo.disabled = false;
		panelSelect.style.display = '';
		
		panelInput.style.display = 'none';
		inputCargo.disabled = true;
	}
}

function startAjaxStatus() {
	document.getElementById("nonAjaxStatus_start").style.display='block'; 
	document.getElementById("nonAjaxStatus_complete").style.display='none'; 
	document.getElementById("nonAjaxStatus_error").style.display='none'; 
	document.getElementById("nonAjaxStatus_default").style.display='none';
}