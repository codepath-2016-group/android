package com.codepath.apps.findmate.models;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;
import java.util.Random;

@ParseClassName("Group")
public class Group extends ParseObject {

    public static final String NAME_KEY = "name";
    public static final String INVITE_KEY = "invite";
    public static final String MEMBERS_KEY = "members";

    private static final Random RANDOM = new Random();

    public static void getGroupsByUser(User user, FindCallback<Group> callback) {
        ParseQuery.getQuery(Group.class)
                .include(Group.MEMBERS_KEY)
                .whereEqualTo(Group.MEMBERS_KEY, user)
                .findInBackground(callback);
    }

    public static void getGroupByInviteCode(String inviteCode, FindCallback<Group> callback) {
        ParseQuery.getQuery(Group.class)
                .include(Group.MEMBERS_KEY)
                .whereEqualTo(Group.INVITE_KEY, inviteCode)
                .findInBackground(callback);
    }

    public static void getGroupById(String id, GetCallback<Group> callback) {
        ParseQuery.getQuery(Group.class)
                .include(Group.MEMBERS_KEY)
                .getInBackground(id, callback);
    }

    // Generate a random six-digit string
    public static String randomInviteCode() {
        return Integer.toString(100000 + RANDOM.nextInt(900000));
    }

    public Group() {
    }

    public String getName() {
        return getString(NAME_KEY);
    }

    public String getInviteCode() {
        return getString(INVITE_KEY);
    }

    public List<User> getMembers() {
        return getList(MEMBERS_KEY);
    }

    public Group setName(String name) {
        put(NAME_KEY, name);
        return this;
    }

    public Group setInviteCode(String inviteCode) {
        put(INVITE_KEY, inviteCode);
        return this;
    }

    public Group addMember(User member) {
        add(MEMBERS_KEY, member);
        return this;
    }

    public Group addMembers(List<User> members) {
        addAllUnique(MEMBERS_KEY, members);
        return this;
    }
}

