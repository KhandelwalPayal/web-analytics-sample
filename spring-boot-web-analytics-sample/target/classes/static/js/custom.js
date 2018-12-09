alert("I'm active");

$(document).ready(function(){
	  $('button').click(function(){
          sp.track('Click Button', {
              button: $(this).data('type'),
              loggedin: false
          });
      });

      $('#selector').change(function(){
          sp.track('Select', {
              selector: $(this).attr('name'),
              option: $(this).find(":selected").text(),
              loggedin: true
          })
      })

    sp.trackLink($('a').first(), 'Click Link A');
    sp.trackLink($('a').last(), 'Click Link B');
});
