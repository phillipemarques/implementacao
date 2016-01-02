<%-- 
    Document   : search_bar
    Created on : 15/12/2015, 11:56:53
    Author     : phillipe
--%>

<div class="jumbotron" style="padding-bottom: 12px; padding-top: 32px;">
    <div class="container">
        <div class="row" style="padding-top: 30px;">
            <div class="col-md-3">
            </div>
            <div class="col-md-6">
                <form method="post" action="UserController?action=search">
                    <fieldset>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="checked" name="workflow" checked disabled>
                                Workflows
                            </label>
                            <label>
                                <input type="checkbox" value="checked" name="file" checked>
                                Files
                            </label>                            
                            <label>
                                <input type="checkbox" value="checked" name="pack" checked>
                                Packs
                            </label>
                        </div>
                        <div class="input-group">
                            <input type="text"
                                   placeholder="Enter the term you want to search"
                                   class="form-control" id="search" name="search"> <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">search</button>
                            </span>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="col-md-3">
            </div>            
        </div>
    </div>
</div>