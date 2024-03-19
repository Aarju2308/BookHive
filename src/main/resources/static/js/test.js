function getSubCat(category){
	console.log(category);
	$.ajax({
		type : "GET",
		url : "/api/subcategorybycategory/"+category,
		success : function(data){
			$("#dropSubCategory").empty();
			for(var i = 0 ; i < data.length ; i++){
				$("#dropSubCategory").append('<option value="'+data[i].subCatId+'">'+data[i].name+'</option>');
			}
		},
		error: function(error) {
            console.error('Error:', error);
        }
	})
}