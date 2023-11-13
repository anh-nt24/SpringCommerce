import React, { useState } from 'react';

const Login = (props) => {
    return (
        <div className='login-wrapper'>
            <div className="login-form">
                <form>
                    <h1>Login</h1>
                    <div className="content">
                        <div className="input-field">
                            <input type="email" placeholder="Email" autocomplete="nope" />
                        </div>
                        <div className="input-field">
                            <input type="password" placeholder="Password" autocomplete="new-password" />
                        </div>
                    </div>
                    <div className="action">
                        <button>Sign in</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;