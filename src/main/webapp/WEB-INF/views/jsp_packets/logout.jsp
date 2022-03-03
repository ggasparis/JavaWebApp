<!DOCTYPE html>
<html lang="en">

<div class="modal fade bd-example-modal-sm" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" style="padding: 30px; background-color: #F7F7F7; margin: 0 auto; border-radius: 2px; width: 100%;">
            <div style="text-align: center; color: #49071c;">
                <h4 style="margin-top: 7px"><b>Are you sure you want to leave?</b></h4>
                <hr class="style">
            </div>
            <form method="post" action="/logout" id="logout">
                <button type="button" class="btn btn-danger my-cansel-btn" data-dismiss="modal">CANCEL</button>
                <input type="submit" name="logout" class="btn btn-success my-success-btn" value="LOGOUT">
            </form>
        </div>
    </div>
</div>

</html>