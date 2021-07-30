package com.zshnb.patterndesign.builder;

import java.util.ArrayList;
import java.util.List;

public class GetUserResponse {
    private List<User> users;
    private int total;
    private GetUserResponse() {
        users = new ArrayList<>();
        total = 0;
    }

    public static class Builder {
        private GetUserResponse response;
        private Builder(GetUserResponse response) {
            this.response = response;
        }

        public Builder addUser(User user) {
            response.users.add(user);
            return this;
        }

        public Builder addAllUser(List<User> users) {
            response.users.addAll(users);
            return this;
        }

        public Builder setTotal(int total) {
            response.total = total;
            return this;
        }

        public GetUserResponse build() {
            return response;
        }
    }

    public static GetUserResponse.Builder newBuilder() {
        return new GetUserResponse.Builder(new GetUserResponse());
    }

    public GetUserResponse.Builder toBuilder() {
        return new GetUserResponse.Builder(this);
    }

    public List<User> getUsers() {
        return users;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "GetUserResponse{" +
            "users=" + users +
            ", total=" + total +
            '}';
    }
}
