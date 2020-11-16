package com.android.appname.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author at-hungtruong
 */
@Entity
data class RepositoryEntity(
     @ColumnInfo(name = "id")
     @PrimaryKey
     var id: Long = 0,
     @ColumnInfo(name = "note_id")
     var noteId: String? = null,
     @ColumnInfo(name = "name")
     var name: String? = null,
     @ColumnInfo(name = "full_name")
     var fullName: String? = null,
     @ColumnInfo(name = "private")
     var private: String? = null,
     @ColumnInfo(name = "html_url")
     var htmlUrl: Boolean? = null,
     @ColumnInfo(name = "description")
     var description: String? = null,
     @ColumnInfo(name = "fork")
     var fork: Boolean? = null,
     @ColumnInfo(name = "url")
     var url: String? = null,
     @ColumnInfo(name = "forks_url")
     var forksUrl: String? = null,
     @ColumnInfo(name = "keys_url")
     var keysUrl: String? = null,
     @ColumnInfo(name = "collaborators_url")
     var collaboratorsUrl: String? = null,
     @ColumnInfo(name = "teams_url")
     var teamsUrl: String? = null,
     @ColumnInfo(name = "hooks_url")
     var hooksUrl: String? = null,
     @ColumnInfo(name = "issue_uvents_url")
     var issueUventsUrl: String? = null,
     @ColumnInfo(name = "events_url")
     var eventsUrl: String? = null,
     @ColumnInfo(name = "assignees_url")
     var assigneesUrl: String? = null,
     @ColumnInfo(name = "branches_url")
     var branchesUrl: String? = null,
     @ColumnInfo(name = "tags_url")
     var tagsUrl: String? = null,
     @ColumnInfo(name = "blobs_url")
     var blobsUrl: String? = null,
     @ColumnInfo(name = "git_tags_url")
     var gitTagsUrl: String? = null,
     @ColumnInfo(name = "git_refs_url")
     var gitRefsUrl: String? = null,
     @ColumnInfo(name = "statuses_url")
     var statusesUrl: String? = null,
     @ColumnInfo(name = "trees_url")
     var treesUrl: String? = null,
     @ColumnInfo(name = "languages_url")
     var languagesUrl: String? = null,
     @ColumnInfo(name = "stargazers_url")
     var stargazersUrl: String? = null,
     @ColumnInfo(name = "contributors_url")
     var contributorsUrl: String? = null,
     @ColumnInfo(name = "subscribers_url")
     var subscribersUrl: String? = null,
     @ColumnInfo(name = "subscription_url")
     var subscriptionUrl: String? = null,
     @ColumnInfo(name = "commits_url")
     var commitsUrl: String? = null,
     @ColumnInfo(name = "git_commits_url")
     var gitCommitsUrl: String? = null,
     @ColumnInfo(name = "contents_url")
     var contentsUrl: String? = null,
     @ColumnInfo(name = "comments_url")
     var commentsUrl: String? = null,
     @ColumnInfo(name = "issue_comment_url")
     var issueCommentUrl: String? = null,
     @ColumnInfo(name = "compare_url")
     var compareUrl: String? = null,
     @ColumnInfo(name = "merges_url")
     var mergesUrl: String? = null,
     @ColumnInfo(name = "downloads_url")
     var downloadsUrl: String? = null,
     @ColumnInfo(name = "archive_url")
     var archiveUrl: String? = null,
     @ColumnInfo(name = "issues_url")
     var issuesUrl: String? = null,
     @ColumnInfo(name = "pulls_url")
     var pullsUrl: String? = null,
     @ColumnInfo(name = "milestones_url")
     var milestonesUrl: String? = null,
     @ColumnInfo(name = "notifications_url")
     var notificationsUrl: String? = null,
     @ColumnInfo(name = "labels_url")
     var labelsUrl: String? = null,
     @ColumnInfo(name = "releases_url")
     var releasesUrl: String? = null,
     @ColumnInfo(name = "deployments_url")
     var deploymentsUrl: String? = null,
     @Embedded
     var owner: RepositoryOwnerEntity? = null,
     @ColumnInfo(name = "created_at")
     var createdAt: String? = null,
     @ColumnInfo(name = "updated_at")
     var updatedAt: String? = null,
     @ColumnInfo(name = "pushed_at")
     var pushedAt: String? = null,
     @ColumnInfo(name = "git_url")
     var gitUrl: String? = null,
     @ColumnInfo(name = "ssh_url")
     var sshUrl: String? = null,
     @ColumnInfo(name = "clone_url")
     var cloneUrl: String? = null,
     @ColumnInfo(name = "svn_url")
     var svnUrl: String? = null,
     @ColumnInfo(name = "homepage")
     var homepage: String? = null,
     @ColumnInfo(name = "size")
     var size: Int? = null,
     @ColumnInfo(name = "stargazers_count")
     var stargazersCount: Int? = null,
     @ColumnInfo(name = "watchers_count")
     var watchersCount: Int? = null,
     @ColumnInfo(name = "has_issues")
     var hasIssues: Boolean? = null,
     @ColumnInfo(name = "language")
     var language: String? = null,
     @ColumnInfo(name = "has_projects")
     var hasProjects: Boolean? = null,
     @ColumnInfo(name = "has_downloads")
     var hasDownloads: Boolean? = null,
     @ColumnInfo(name = "has_wiki")
     var hasWiki: Boolean? = null,
     @ColumnInfo(name = "has_pages")
     var hasPages: Boolean? = null,
     @ColumnInfo(name = "forks_count")
     var forksCount: Int? = null,
     @ColumnInfo(name = "mirror_url")
     var mirrorUrl: String? = null,
     @ColumnInfo(name = "archived")
     var archived: Boolean? = null,
     @ColumnInfo(name = "disabled")
     var disabled: String? = null,
     @ColumnInfo(name = "open_issues_count")
     var openIssuesCount: Int? = null,
     @Embedded
     var licenses: LicenseEntity? = null,
     @ColumnInfo(name = "forks")
     var forks: Int? = null,
     @ColumnInfo(name = "open_issues")
     var openIssues: Int? = null,
     @ColumnInfo(name = "watchers")
     var watchers: Int? = null,
     @ColumnInfo(name = "default_branch")
     var defaultBranch: String? = null,
     @ColumnInfo(name = "score")
     var score: Float? = null
)
