
export class User{


    username : string;
    fullname : string;
    email : string;
    password : string;

    public User( username : string, fullname : string, email : string, password : string) {

        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public getUsername() : string {
        return this.username;
    }

    public setUsername(username : string) {
        this.username = username;
    }

    public getFullname() : string {
        return this.fullname;
    }

    public setFullname(fullname : string) {
        this.fullname = fullname;
    }

    public getEmail() : string {
        return this.email;
    }

    public setEmail(email : string) {
        this.email = email;
    }

    public getPassword() : string {
        return this.password;
    }

    public setPassword(password : string) {
        this.password = password;
    }
}
