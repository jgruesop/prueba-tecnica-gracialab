<template>      
    <div class="m-5 mx-auto">
        <div class="row">
            <div class="card-header bg-dark text-white text-center mx-auto m-3 w-50">
                <h1>INICIO SESI&Oacute;N</h1>
            </div>
            <div>
                <form >
                    <div class="form-group w-25 m-3 mx-auto">
                        <label for="email" class="control-label" name="username" >Email :</label>
                        <input type="text" id="email" class="form-control" required placeholder="Ingrese su Email">
                    </div>

                    <div class="form-group w-25 m-3 mx-auto">
                        <label for="password" class="control-label" name="password" >Contrase&ntilde;a :</label>
                        <input type="password" id="password" class="form-control" required placeholder="ingrese su contrase&ntilde;a">
                    </div>

                    <br>
                    <div class="form-group w-50 justify-content-lg-center mx-auto">
                        <div class="row w-50 mx-auto">
                            <div class="col-md-offset-3">
                                <input type="submit" class="form-control btn-lg btn btn-primary" id="login-submit" value="Ingresar"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>
