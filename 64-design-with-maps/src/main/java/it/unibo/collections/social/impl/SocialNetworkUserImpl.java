/**
 * 
 */
package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    /*
     *
     * [FIELDS]
     *
     * Define any necessary field
     *
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map:  think of what type of keys and values would best suit the requirements
     */
    Map<String, Set<U>> followedUsers = new HashMap<>();
    private static final boolean USER_ALREADY_FOLLOWED = false;
    private static final boolean USER_FOLLOWED_SUCCESSFULLY = true;
    /*
     * [CONSTRUCTORS]
     *
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     *
     * - firstName
     * - lastName
     * - username
     * - age and every other necessary field
     */
    /**
     * Builds a user participating in a social network.
     *
     * @param name
     *            the user firstname
     * @param surname
     *            the user lastname
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
    }

    /*
     * 2) Define a further constructor where the age defaults to -1
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String user) {
        super(name, surname, user);
    }
    /*
     * [METHODS]
     *
     * Implements the methods below
     */
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        if (!isFollowed(user)) {
            if (isGroupNew(circle)) {
                addGroup(circle);
                followedUsers.get(circle).add(user);
                return USER_FOLLOWED_SUCCESSFULLY;
            } else {
                this.followedUsers.get(circle).add(user);
                return USER_FOLLOWED_SUCCESSFULLY;
            }
        } else {
            return USER_ALREADY_FOLLOWED;
        }

        /*
        Set<U> group = this.followedUsers.get(circle);
            if (group == null) {
                group = new HashSet<>();
                followedUsers.put(circle, group);
            }
        return group.add(user);
        */
    }

    /**
     *
     * [NOTE] If no group with groupName exists yet, this implementation must
     * return an empty Collection.
     */
    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        if (isGroupNew(groupName)) {
            return Collections.emptyList();
        } else {
            return new HashSet<>(this.followedUsers.get(groupName));
        }
    }

    @Override
    public List<U> getFollowedUsers() {
        final List<U> allFollowedUsers = new ArrayList<U>();
        for (var group : this.followedUsers.values()) {
            allFollowedUsers.addAll(group);
        }
        return allFollowedUsers;
    }

    /**
     * checks if the given user is already followed
     * 
     * @return
     *          true if user is already followed, false otherwise
     */
    private boolean isFollowed(final U user) {
        for (var group : this.followedUsers.values()) {
            if (group.contains(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the given group already exists
     * 
     * @return
     *      true if the group does not exist, false otherwise
     */
    private boolean isGroupNew(final String group) {
        return !this.followedUsers.containsKey(group);
    }

    /**
     * adds a new entry in followedUsers where the key is the
     * given group name and the value is a new Set of the group
     * 
     * @param group
     * @return
     *      a Set of the group's followed users associated to the key
     */
    private void addGroup(final String group) {
        this.followedUsers.put(group, new HashSet<U>());
    }
}
