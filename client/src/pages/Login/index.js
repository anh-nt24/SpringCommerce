import Button from 'react-bootstrap/Button';
import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { UserLogin } from '../../api/UserApi';
import { useCookies } from 'react-cookie';

const Login = (props) => {
    const config = {
        'email': '',
        'password': '',
    }
    let navigate = useNavigate()

    const [form, setForm] = useState(config);
    const [cookies, setCookie] = useCookies(['name', 'id', 'token']);

    const handleChange = (e) => {
        const {value, name} = e.target
        setForm(prevState => ({
            ...prevState,
            [name]: value
        }));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await UserLogin(form);
        if (response.status === 200) {
            const { name, id, token } = response.data['data'];
            setCookie('name', name);
            setCookie('id', id);
            setCookie('token', token);
            navigate('/')
        }
    }
    return (
        <div className='login-wrapper'>
            <div className="login-form">
                <form onSubmit={handleSubmit}>
                    <h1>Login</h1>
                    <div className="content">
                        <div className="input-field">
                            <input onChange={handleChange} name="email" value={form.email} type="email" placeholder="Email" autoComplete="nope" />
                        </div>
                        <div className="input-field">
                            <input onChange={handleChange} name="password" value={form.password} type="password" placeholder="Password" autoComplete="new-password" />
                        </div>
                    </div>
                    <div className="action">
                        <Button type='submit' variant="primary">Sign in</Button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;