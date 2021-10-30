
export class User{

   
    username : string;
    fullName : string;
    email : string;
    password : string;

    public User( username : string, fullName : string, email : string, password : string) {
        
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public getUsername() : string {
        return this.username;
    }

    public setUsername(username : string) {
        this.username = username;
    }

    public getFullName() : string {
        return this.fullName;
    }

    public setFullName(fullName : string) {
        this.fullName = fullName;
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
