String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

//$(".answer-write input[type = submit]").click(addAnswer);

$(".answer-write input[type = submit]").on('click', addAnswer);

function addAnswer(e) {
  e.preventDefault();
  console.log("clicked");

  var queryString = $(".answer-write").serialize();
  console.log("query : " + queryString);

  $.ajax({
    type : 'post',
    url : $(".answer-write").attr("action"),
    data : queryString,
    dataType : 'json',
    error : onError,
    success : onSuccess
  });
}

function onError(xhr, status) {
  console.log("Error");
}

function onSuccess(data, status) {
  console.log(data);
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.question.id, data.id);
  $(".qna-comment-slipp-articles").prepend(template);
  $(".answer-write textarea").val("");
}

$(".link-delete-answer").on('click', function(e) {
  e.preventDefault();

  let deleteBtn = $(this);
  let url = deleteBtn.attr("href");
  console.log(url);

  $.ajax({
    type : 'delete',
    url : url,
    dataType : 'json',
    error : function (xhr, status) {
    },
    success : function (data, status) {
      console.log(data);
      if (data.valid) {
        deleteBtn.closest("article").remove();
      } else {
        alert(data.errorMessage);
      }
    }
  });
});